package devsearch.developers.ws.shared.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class StrictMapperImpl implements Mapper {

    private ModelMapper modelMapper;

    public StrictMapperImpl() {
	modelMapper = new ModelMapper();
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
	return modelMapper.map(source, destinationType);
    }

}
