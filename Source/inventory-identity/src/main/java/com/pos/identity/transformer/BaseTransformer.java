package com.pos.identity.transformer;

public interface BaseTransformer<T, I> {

	I tranform(T type);

	T reverseTranform(I type);

}
