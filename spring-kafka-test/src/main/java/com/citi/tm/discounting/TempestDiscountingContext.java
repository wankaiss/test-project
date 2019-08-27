package com.citi.tm.discounting;

import com.citi.tm.discounting.override.TempestOverrideContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TempestOverrideContext.class)
public class TempestDiscountingContext {
}
