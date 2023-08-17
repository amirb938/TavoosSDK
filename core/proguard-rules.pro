# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Keep public classes and methods that are referenced externally
-keep public class ir.fastclick.core.** { public *; }

# Keep the entry point of your library
-keepclassmembers class ir.fastclick.core.TavoosSDK {
    public <init>();
}

# Keep methods related to retrieving the advertising ID
-keep class com.google.android.gms.ads.identifier.** { *; }

# Keep resource names
-keepclassmembers class **.R$* {
    public static <fields>;
}

# If you use Kotlin, keep metadata and annotations for reflection
-keep class kotlin.Metadata { *; }
-keepclassmembers class * {
    @kotlin.Metadata <methods>;
}
