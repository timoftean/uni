package ro.ubb.catalog.web.converter;

/**
 * Created by Nicu on 5/8/17.
 */
public interface ConverterGeneric<Model, Dto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}

