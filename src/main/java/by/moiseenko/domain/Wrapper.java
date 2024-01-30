package by.moiseenko.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
    @author Ilya Moiseenko on 30.01.24
*/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wrapper<T> {

    private List<T> tickets;
}
