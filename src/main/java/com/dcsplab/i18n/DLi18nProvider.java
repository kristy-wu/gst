package com.dcsplab.i18n;

import com.vaadin.flow.i18n.I18NProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.TRADITIONAL_CHINESE;
import static java.util.ResourceBundle.getBundle;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.success;

public class DLi18nProvider implements I18NProvider {
  public static final String RESOURCE_BUNDLE_NAME = "gstFramework";
  private static final Logger logger = LoggerFactory.getLogger(DLi18nProvider.class);
  private static final long serialVersionUID = 7859412111671598813L;
  private static final ResourceBundle RESOURCE_BUNDLE_EN = getBundle(RESOURCE_BUNDLE_NAME, ENGLISH);
  
  private static final ResourceBundle RESOURCE_BUNDLE_ZH =
    getBundle(RESOURCE_BUNDLE_NAME, TRADITIONAL_CHINESE);
  
  private static final List<Locale> providedLocales = List.of(ENGLISH, TRADITIONAL_CHINESE);
  
  @Override
  public List<Locale> getProvidedLocales() {
    return providedLocales;
  }
  
  @Override
  public String getTranslation(String key, Locale locale, Object... params) {
    return match(
      matchCase(() -> success(RESOURCE_BUNDLE_EN)),
      matchCase(() -> TRADITIONAL_CHINESE.equals(locale), () -> success(RESOURCE_BUNDLE_ZH)),
      matchCase(() -> ENGLISH.equals(locale), () -> success(RESOURCE_BUNDLE_EN)))
      .map(
        resourceBundle -> {
          if (!resourceBundle.containsKey(key)) {
            logger.info("missing ressource key: " + key);
          }
          return (resourceBundle.containsKey(key)) ? resourceBundle.getString(key) : key;
        })
      .getOrElse(() -> key + " - " + locale);
  }
}
