package org.acme.exception;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

import javax.decorator.Decorator;
import javax.enterprise.context.ApplicationScoped;

@Localized("en")
public interface EnAppMessages extends AppMessages {

    @Override
    @Message("Error En")
    String genericError();

}
