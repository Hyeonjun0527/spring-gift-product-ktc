package study;

import io.micrometer.common.lang.NonNull;

public record CreateWishRequest(@NonNull Long productId, @NonNull Integer quantity) {

}
