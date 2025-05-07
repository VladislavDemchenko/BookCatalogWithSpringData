package bookcatalog;

import org.bookcatalog.dto.CatalogDto;
import org.bookcatalog.entity.Catalog;
import org.bookcatalog.exceptions.InvalidRequestException;
import org.bookcatalog.exceptions.NotFoundContentException;
import org.bookcatalog.repository.CatalogRepository;
import org.bookcatalog.service.impl.CatalogServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {

    @InjectMocks
    private CatalogServiceImpl catalogService;

    @Mock
    private CatalogServiceImpl catalogServiceSpy;

    @Mock
    private CatalogRepository catalogRepository;

    @Mock
    private BindingResult bindingResult;

    private static CatalogDto catalogDto;
    private static Catalog catalog;

    @Test
    void findByCatalogName_SuccessfulTest() {

        when(catalogRepository.findByCatalogName("vasya")).thenReturn(Optional.of(catalog));

        String stringOfCatalog = catalogService.findByCatalogName("vasya");

        assertEquals(stringOfCatalog, catalog.toString());
    }
    @Test
    void findByCatalogName_NotFoundExceptionTest() {
        when(catalogRepository.findByCatalogName("vasya")).thenThrow(NotFoundContentException.class);

        Assertions.assertThrows(NotFoundContentException.class,
                () -> catalogService.findByCatalogName("vasya"));
    }

    @Test
    void save_SuccessfulTest(){

        when(catalogRepository.save(catalog)).thenReturn(catalog);

        assertEquals(catalogService.save(catalogDto, bindingResult),
                new Catalog(catalogDto).toString());

    }
    @Test
    void save_InvalidRequestException_ByBindingResultTest(){

        when(bindingResult.hasErrors()).thenReturn(true);

        Assertions.assertThrows(InvalidRequestException.class,
                () -> catalogService.save(catalogDto, bindingResult), bindingResult.getAllErrors().toString());
    }

    @Test
    void save_InvalidfRequestException_ByAlreadyExistTest(){

        when(catalogRepository.save(new Catalog(catalogDto))).thenThrow(new DataIntegrityViolationException(""));

        Assertions.assertThrows(InvalidRequestException.class,
                () -> catalogService.save(catalogDto, bindingResult));

    }
    @Test
    void save_verifyMethodOnCallTest(){

        when(catalogRepository.save(catalog)).thenReturn(catalog);

        catalogService.save(catalogDto, bindingResult);

        verify(catalogRepository, Mockito.times(1)).save(catalog);

    }

    @BeforeAll
    static void beforeAll() {
        catalogDto = new CatalogDto("vasya", "my favorite books");
        catalog = new Catalog(catalogDto);
    }
}
