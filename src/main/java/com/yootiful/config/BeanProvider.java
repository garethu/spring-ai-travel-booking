package com.yootiful.config;

import com.yootiful.entities.Booking;
import com.yootiful.mapper.BookingMapper;
import com.yootiful.service.*;
import com.yootiful.models.BookingRecord;
import com.yootiful.models.Quotation;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.function.Function;

@Configuration
public class BeanProvider {
    @Bean
    @Description("Provides the current quotation of a cryptocurrency by symbol")
    public Function<QuotationFunction.Request, Quotation> getQuotation(QuotationService quotationService) {
        return request -> {
            try {
                return quotationService.fetch(request.symbol());
            }
            catch (Exception e) {
                return new Quotation(null,null,0.0,0.0,0.0);
            }
        };
    }
    @Bean
    @Description("Get the booking details")
    public Function<BookingFunction.Request, BookingRecord> getBookingDetails(BookingService bookingService) {
        System.out.println("###### Getting the booking details ######");
        return request -> {
            try {
                Booking booking = bookingService.getBookingByIdAndFirstNameAndLastName(request.id(), request.firstName(), request.lastName());
                return BookingMapper.toRecord(booking);
            }
            catch (Exception e) {
                return new BookingRecord(null,null,null,null,null,null ,null,null);
            }
        };
    }

    @Bean
    @Description("Cancel the booking")
    public Function<BookingCancelFunction.Request, BookingRecord> cancelBooking(BookingService bookingService) {
        System.out.println("###### Cancel the booking ######");
        return request -> {
            try {
                Booking booking = bookingService.deleteBooking(request.id());
                return BookingMapper.toRecord(booking);
            }
            catch (Exception e) {
                return new BookingRecord(null,null,null,null,null,null ,null,null);
            }
        };
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        ignoreCertificates();
        return builder.build();
    }

    private void ignoreCertificates() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
        }
    }

 /*   @Bean
    public ChatMemory chatMemory() {
        return new InMemoryChatMemory();
    }*/
}
