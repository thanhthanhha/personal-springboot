package com.minibank.cst.cust.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.DisplayName;

import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minibank.cst.cust.service.CustService;
import com.minibank.cst.cust.dto.RetrieveCustOutDto;
import com.minibank.exception.BusinessException;
import com.minibank.cst.cust.repository.CustRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.Mockito;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustServiceTest_retrieveCust  {


    @MockBean
    private CustRepository custRepositoryMockBean;

    @SpyBean
    private CustService custService;

    @BeforeAll
    public static void init() {
    }      

    @BeforeEach
    public void setup(TestInfo testInfo) {
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
    }

    @Test
    @DisplayName("Throws BusinessException when customer number is null")
    public void retrieveCustTest_0_nullCustNo() throws Exception{
        /* Test Stub */ {
            Mockito.doReturn(createMockOutputRetrieveCustOutDto0())
                   .when(custRepositoryMockBean)
                   .retrieveCust(Mockito.any(String.class));
        }


        /* given */

        /* when */
        Throwable exception = assertThrows(BusinessException.class, ()-> {
            custService.retrieveCust((String)null);
        });
        
        /* then */
        assertSame(BusinessException.class, exception.getClass());
    }

    @Test
    @DisplayName("Throws BusinessException when customer number is an empty string.")
    public void retrieveCustTest_1_emptyCustNo() throws Exception{
        /* Test Stub */ {
            Mockito.doReturn(createMockOutputRetrieveCustOutDto1())
                   .when(custRepositoryMockBean)
                   .retrieveCust(Mockito.any(String.class));
        }


        /* given */
        String custNo = "";

        /* when */
        Throwable exception = assertThrows(BusinessException.class, ()-> {
            custService.retrieveCust(custNo);
        });
        
        /* then */
        assertSame(BusinessException.class, exception.getClass());
    }

    @Test
    @DisplayName("Throws BusinessException when customer number is a whitespace string.")
    public void retrieveCustTest_2_blankCustNo() throws Exception{
        /* Test Stub */ {
            Mockito.doReturn(createMockOutputRetrieveCustOutDto2())
                   .when(custRepositoryMockBean)
                   .retrieveCust(Mockito.any(String.class));
        }


        /* given */
        String custNo = "   ";

        /* when */
        Throwable exception = assertThrows(BusinessException.class, ()-> {
            custService.retrieveCust(custNo);
        });
        
        /* then */
        assertSame(BusinessException.class, exception.getClass());
    }

    @Test
    @DisplayName("Tests whether BusinessException is thrown when a non-existent customer number is provided")
    public void retrieveCustTest_3_nonExistentCustNo() throws Exception{
        /* Test Stub */ {
            Mockito.doReturn(createMockOutputRetrieveCustOutDto3())
                   .when(custRepositoryMockBean)
                   .retrieveCust(Mockito.any(String.class));
        }


        /* given */
        String custNo = "1234567890";

        /* when */
        Throwable exception = assertThrows(BusinessException.class, ()-> {
            custService.retrieveCust(custNo);
        });
        
        /* then */
        assertSame(BusinessException.class, exception.getClass());
    }

    @Test
    @DisplayName("Returns a valid RetrieveCustOutDto when a valid customer number is provided")
    public void retrieveCustTest_4_validCustNo() throws Exception{
        /* Test Stub */ {
            Mockito.doReturn(createMockOutputRetrieveCustOutDto4())
                   .when(custRepositoryMockBean)
                   .retrieveCust(Mockito.any(String.class));
        }


        /* given */
        String custNo = "1234567890";

        /* when */
        RetrieveCustOutDto result = custService.retrieveCust(custNo);

        /* then */
        assertThat(result.getCustNo()).isEqualTo("1234567890");
        assertThat(result.getCustNm()).isEqualTo("John Doe");
        assertThat(result.getCustEnnm()).isEqualTo("John Doe");
        assertThat(result.getCpno()).isEqualTo("CP001");
        assertThat(result.getEmaddr()).isEqualTo("john.doe@example.com");
    }


    private RetrieveCustOutDto createMockOutputRetrieveCustOutDto0() {
        return null;
    }
    
    private RetrieveCustOutDto createMockOutputRetrieveCustOutDto1() {
        return null;
    }
    
    private RetrieveCustOutDto createMockOutputRetrieveCustOutDto2() {
        return null;
    }
    
    private RetrieveCustOutDto createMockOutputRetrieveCustOutDto3() {
        return null;
    }
    
    private RetrieveCustOutDto createMockOutputRetrieveCustOutDto4() {
        RetrieveCustOutDto retrievecustoutdto = RetrieveCustOutDto.builder()
                  .custNo("1234567890")
                  .custNm("John Doe")
                  .custEnnm("John Doe")
                  .cpno("CP001")
                  .emaddr("john.doe@example.com")
                  .build();
        return retrievecustoutdto;
    }
    
}