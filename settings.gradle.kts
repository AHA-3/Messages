// Workaround for AGP AndroidLocationsException when multiple preference environment variables
// (e.g. ANDROID_PREFS_ROOT and ANDROID_USER_HOME) are defined in the OS environment.
@Suppress("UNCHECKED_CAST")
try {
    val processEnvClass = Class.forName("java.lang.ProcessEnvironment")
    val envField = processEnvClass.getDeclaredField("theEnvironment").apply { isAccessible = true }
    val env = envField.get(null) as? MutableMap<String, String>

    val ciEnvField = processEnvClass.getDeclaredField("theCaseInsensitiveEnvironment").apply { isAccessible = true }
    val ciEnv = ciEnvField.get(null) as? MutableMap<String, String>

    if (System.getenv("ANDROID_USER_HOME") != null) {
        env?.remove("ANDROID_PREFS_ROOT")
        env?.remove("ANDROID_SDK_HOME")
        ciEnv?.remove("ANDROID_PREFS_ROOT")
        ciEnv?.remove("ANDROID_SDK_HOME")
    } else if (System.getenv("ANDROID_PREFS_ROOT") != null && System.getenv("ANDROID_SDK_HOME") != null) {
        env?.remove("ANDROID_SDK_HOME")
        ciEnv?.remove("ANDROID_SDK_HOME")
    }
} catch (_: Throwable) {
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://www.jitpack.io") }
        mavenLocal()
    }
}
include(":app")
rootProject.name = "Messages"
