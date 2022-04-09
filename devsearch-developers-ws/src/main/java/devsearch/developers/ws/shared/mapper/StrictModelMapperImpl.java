package devsearch.developers.ws.shared.mapper;

import org.springframework.stereotype.Component;

import devsearch.common.utils.mapper.Mapper;
import devsearch.common.utils.mapper.StrictMapperImpl;

@Component
public class StrictModelMapperImpl implements ModelMapper {

    private Mapper modelMapper = new StrictMapperImpl();

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
	return this.modelMapper.map(source, destinationType);
    }

}
