Softwareproduktlinien Übung 6:
  Beide implementierten Mechanismen legen zur Compilezeit fest, welche Feature aus der Softwareproduktlinie
  in das Produkt übernommen wird.   
  Der Code nicht verwendeter Feature wird zur Compilezeit entfernt.

Der entsprechende Branch für den Variabilitätsmechanismus muss aus dem Git gewählt werden und AndroidStudio geöffnet werden.

Variabilitätsmechanismus 1:  
    * verfügbar auf Branch IntegratedPreprocess  
    * Feature können in der FeatureConfiguration.kt an- und abgewählt werden  
    * FeatureConfiguration ist schon zur Compilezeit bekannt  
    * FeatureConfiguration wird in Bedingungen genutzt  
    * im BuildVariants-Tab muss nun Release ausgewählt werden  
    * im Terminal muss nun "./gradlew assembleRelease" ausgeführt werden  
    * jetzt wird Code und Codeverweise aus unreachable Conditionals von ProGuard zur Compilezeit entfernt  
    * die konfigurierte App liegt nun als APK in "../UniversalGameHelper/UGH-Android/app/build/outputs/apk/release/" und kann auf dem Smartphone installiert werden  
    
    
Variabilitätsmechanismus 2:  
    * verfügbar auf Branch externalPreprocessing  
    * nutz Android Flavours in Kombination mit einem Gradle-Preprocessor-Plugin(https://github.com/dannyjiajia/gradle-java-preprocessor-plugin)  
    * Verfügbare Varianten werden als Flavor in der build.gradle definiert und für jede Variante Preprocessorsymbole definiert  
    * mit Hilfe der Symbole kann nun Präprozessorlogik wie in C verwendet werden (#ifdef und #endif)  
    * soll nun eine Variante gebaut werden muss im Terminal mit "./gradlew assembleVariantXRelease" die gewünschte Variante gebaut werden
      (X ist hierbei die Variantennummer)  
    * die konfigurierte App liegt nun als APK in "../UniversalGameHelper/UGH-Android/app/build/outputs/apk/variantX/release/" und kann auf dem Smartphone installiert werden  
