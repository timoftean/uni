package ro.ubb.catalog.web.converter;

import ro.ubb.catalog.core.model.BaseEntity;
import ro.ubb.catalog.web.dto.BaseDto;
/**
 * Created by Nicu on 4/24/17.
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends ConverterGeneric<Model, Dto> {

}
