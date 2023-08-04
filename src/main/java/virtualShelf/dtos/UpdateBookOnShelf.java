package virtualShelf.dtos;

import virtualShelf.enums.EStatus;

public record UpdateBookOnShelf (
        Boolean favorite,
        EStatus status
){
}
