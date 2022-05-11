package com.pos.inventory.repository.transfomer;

public interface BaseTransformer<T, I> {

	I transform(T type);

	T reverseTransform(I type);

}
