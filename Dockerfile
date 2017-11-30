# Start with JDK8
FROM java:8-jdk

# Init dependencies for the setup process
RUN dpkg --add-architecture i386 \
    && apt-get update \
    && apt-get install -y \
        software-properties-common \
        unzip \
        wget \
        make \
        gcc \
	tclsh \
    # Install 32-bit compatibility for 64-bit environments
        libc6:i386 \
        libncurses5:i386 \
        libstdc++6:i386 \
        zlib1g:i386 \
    # Cleanup
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Set desired Android Linux SDK version
ENV ANDROID_SDK_VERSION 25.2.3

ENV ANDROID_SDK_ZIP tools_r${ANDROID_SDK_VERSION}-linux.zip
ENV ANDROID_SDK_ZIP_URL https://dl.google.com/android/repository/${ANDROID_SDK_ZIP}
ENV ANDROID_HOME /opt/android-sdk-linux

ENV PATH ${PATH}:${ANDROID_HOME}/tools

# Install Android SDK
RUN mkdir -p ${ANDROID_HOME}

RUN cd ${ANDROID_HOME} \
  && wget --quiet ${ANDROID_SDK_ZIP_URL} \
  && unzip -q ${ANDROID_HOME}/${ANDROID_SDK_ZIP} -d ${ANDROID_HOME} \
  && rm ${ANDROID_HOME}/${ANDROID_SDK_ZIP}

RUN mkdir -p ${HOME}/.android

RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager "build-tools;25.0.3"
RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager "build-tools;26.0.2"
RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager "platform-tools"
RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager "platforms;android-26"
RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager "cmake;3.6.4111459"
RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager "ndk-bundle"

ENV ANDROID_NDK_HOME ${ANDROID_HOME}/ndk-bundle

ENV GRADLE_VERSION=4.1
RUN curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -O \
  && unzip -q gradle-${GRADLE_VERSION}-bin.zip \
  && ln -s "/usr/lib/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle

ENV GRADLE_HOME=/app/gradle-${GRADLE_VERSION}
