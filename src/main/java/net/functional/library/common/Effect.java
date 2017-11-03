package net.functional.library.common;

public interface Effect<T> {
  void apply(T t);
}