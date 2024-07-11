To get a intall the Modi SDK on your project project into your build:

Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
                    maven ("https://maven.regulaforensics.com/RegulaDocumentReader")
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.Tablu-Tech:modi-sdk:Tag'
	}
Share this release:

Link
That's it! The first time you request a project JitPack checks out the code, builds it and serves the build artifacts (jar, aar).
If the project doesn't have any GitHub Releases you can use the short commit hash or 'master-SNAPSHOT' as the version.

