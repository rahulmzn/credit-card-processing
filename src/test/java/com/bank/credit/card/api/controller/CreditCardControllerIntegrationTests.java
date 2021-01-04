package com.bank.credit.card.api.controller;

import com.bank.credit.card.api.CreditCardProcessingApplication;
import com.bank.credit.card.api.builder.CreditCardBuilder;
import com.bank.credit.card.api.constraints.CardNumber;
import com.bank.credit.card.api.exceptions.InvalidRequestException;
import com.bank.credit.card.api.model.Brand;
import com.bank.credit.card.api.model.Card;
import com.bank.credit.card.api.service.CreditCardService;
import com.bank.credit.card.api.util.ResourcePaths;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static com.bank.credit.card.api.util.Constants.PojoDescription.*;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCardProcessingApplication.class)
@WebAppConfiguration
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class CreditCardControllerIntegrationTests {


    protected ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(SerializationFeature.INDENT_OUTPUT, true);

    private MockMvc mockMvc;

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext wac;

    @Resource
    CreditCardService creditCardService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(documentationConfiguration(this.restDocumentation).snippets()).build();
        MockitoAnnotations.openMocks(this);
        assertNotNull(mockMvc);
    }

    public HttpHeaders getRequestHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip");
        return httpHeaders;

    }

    public HttpHeaders getResponseHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_ENCODING, "gzip");
        return httpHeaders;

    }

    @Test
    public void shouldVerifyIfCardHasNoData() throws Exception {
        Card card = CreditCardBuilder.builder().build().buildCard();
        mockMvc.perform(post(ResourcePaths.Card.V1.ROOT).contentType(MediaType.APPLICATION_JSON).headers(getRequestHeaders()).content(mapper.writeValueAsString(card)))
                .andDo(print())  // to verify if exception has been logged for analysis
                .andExpect(status().isUnprocessableEntity())
                .andExpect((jsonPath("$", notNullValue())))
                .andExpect((jsonPath("$.code", notNullValue())))
                .andExpect((jsonPath("$.message", notNullValue())))
                .andExpect((jsonPath("$.exception", hasToString(InvalidRequestException.class.getSimpleName()))))
                .andExpect(jsonPath("$.errors", hasSize(5)))
                .andDo(document("." + ResourcePaths.Card.V1.ROOT + "/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("code").type(JsonFieldType.STRING).description("The code id of problem. Use it for support only"),
                                fieldWithPath("exception").type(JsonFieldType.STRING).description("Exception"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("The message to explain the problem"),
                                subsectionWithPath("errors").type(JsonFieldType.ARRAY).description("Array with constraint problems"))))
                .andReturn();
    }

    @Test
    public void verifyIfCardWasCreated() throws  Exception {
        Card card = CreditCardBuilder.builder().brand(Brand.VISA).balance(10.0).limit("1000").name("Rahul Kumar").number("4716651077977392").build().buildCard();
        card.setId("1234");
        assertNotNull("Card is null!", card);
        mockMvc.perform(post(ResourcePaths.Card.V1.ROOT).contentType(MediaType.APPLICATION_JSON).headers(getRequestHeaders()).content(mapper.writeValueAsString(card)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("." + ResourcePaths.Card.V1.ROOT + "/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(fieldWithPath("id").type(JsonFieldType.STRING).description(ID),
                                fieldWithPath("name").type(JsonFieldType.STRING).description(NAME).attributes(key("constraints").value(NotNull.class.getSimpleName())),
                                fieldWithPath("number").type(JsonFieldType.STRING).description(CARD_NUMBER).attributes(key("constraints").value(CardNumber.class.getSimpleName())),
                                fieldWithPath("limit").type(JsonFieldType.STRING).description(LIMIT).attributes(key("constraints").value(NotNull.class.getSimpleName())),
                                fieldWithPath("brand").type(JsonFieldType.STRING).description(BRAND).type(JsonFieldType.STRING).attributes(key("constraints").value(NotNull.class.getSimpleName())),
                                fieldWithPath("balance").type(JsonFieldType.NUMBER).optional().description(BALANCE).type(JsonFieldType.NUMBER).attributes(key("constraints").value(NotNull.class.getSimpleName()))))).andReturn();

    }

    @Test
    public void verifyIfCardsCreatedExists() throws Exception {

        Card card = CreditCardBuilder.builder().brand(Brand.VISA).balance(10.0).limit("1000").name("Rahul Kumar").number("4716651077977392").build().buildCard();

        card = creditCardService.addCard(card);
        assertNotNull("Cars  is null!", card);

        mockMvc.perform(get(ResourcePaths.Card.V1.ROOT, card.getId()).contentType(MediaType.APPLICATION_JSON).headers(getRequestHeaders()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", notNullValue())))
                .andExpect((jsonPath("$.[0].name", notNullValue())))
                .andExpect((jsonPath("$.[0].number", notNullValue())))
                .andExpect((jsonPath("$.[0].balance", notNullValue())))
                .andExpect((jsonPath("$.[0].limit", notNullValue())))
                .andExpect(jsonPath("$.[0].brand", notNullValue()))
                .andDo(document("." + ResourcePaths.Card.V1.ROOT + "/{method-name}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(fieldWithPath("[].id").type(JsonFieldType.STRING).description(ID),
                                subsectionWithPath("[].name").type(JsonFieldType.STRING).description(NAME).attributes(key("constraints").value(NotNull.class.getSimpleName())),
                                subsectionWithPath("[].number").type(JsonFieldType.STRING).description(CARD_NUMBER).attributes(key("constraints").value(CardNumber.class.getSimpleName())),
                                subsectionWithPath("[].balance").type(JsonFieldType.NUMBER).optional().description(BALANCE).attributes(key("constraints").value(NotNull.class.getSimpleName())),
                                subsectionWithPath("[].limit").type(JsonFieldType.STRING).description(LIMIT).type(JsonFieldType.STRING).attributes(key("constraints").value(NotNull.class.getSimpleName())),
                                subsectionWithPath("[].brand").type(JsonFieldType.STRING).description(BRAND).type(JsonFieldType.STRING).attributes(key("constraints").value(NotNull.class.getSimpleName())))))
                .andReturn();
    }




}
