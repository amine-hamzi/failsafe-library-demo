package com.example.failsafe.demo;

import dev.failsafe.Failsafe;
import dev.failsafe.RetryPolicy;
import dev.failsafe.function.CheckedPredicate;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

public class FailsafeUtil {

    public FailsafeUtil() {
        //utility class
    }

    public static <R> R getNotNull(Supplier<R> supplier) {
        return get(supplier, Objects::nonNull);
    }

    public static <R> R get(Supplier<R> supplier, CheckedPredicate<Object> predicate) {
        RetryPolicy<Object> retryPolicy = RetryPolicy.builder()
                .withDelay(Duration.ofMillis(200))
                .abortIf(predicate)
                .withMaxRetries(3).build();
        return Failsafe.with(retryPolicy).get(supplier::get);
    }
}
