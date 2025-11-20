package domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Producer {
    final Integer id;
    final String name;
}
