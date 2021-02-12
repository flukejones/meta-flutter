SUMMARY = "Flutter Gallery Example"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=cdef5aa41c7ac150f56d004be067470f"

FILESEXTRAPATHS_prepend_poky := "${THISDIR}/files:"

SRC_URI = "git://github.com/flutter/gallery.git;protocol=https;tag=${SRCREV} \
           file://ca-certificates.crt \
           file://intl_0.17.0.patch \
           file://LICENSE"

SRCREV = "v2.8.1"

DEPENDS = "flutter-sdk-native curl-native unzip-native"

S = "${WORKDIR}/git"

do_patch() {
    cd ${S}
    if test -f "pubspec.yaml"; then
        git checkout pubspec.yaml
    fi
    git apply ../intl_0.17.0.patch
}

do_configure() {

    export CURL_CA_BUNDLE=${WORKDIR}/ca-certificates.crt
    export SSH_AUTH_SOCK=${SSH_AUTH_SOCK}
    export SSH_AGENT_PID=${SSH_AGENT_PID}

    PATH=${STAGING_DATADIR_NATIVE}/flutter/sdk/bin:${PATH} flutter config --enable-linux-desktop
    PATH=${STAGING_DATADIR_NATIVE}/flutter/sdk/bin:${PATH} flutter pub get
}

do_compile() {

    PATH=${STAGING_DATADIR_NATIVE}/flutter/sdk/bin:${PATH} flutter build bundle
}

do_install() {

    cd ${S}/build/flutter_assets
    install -d ${D}${datadir}/flutter/examples/flutter_gallery
    cp -rv . ${D}${datadir}/flutter/examples/flutter_gallery
    rm ${D}${datadir}/flutter/examples/flutter_gallery/.last_build_id
}

FILES_${PN} = "${datadir}/flutter/examples/flutter_gallery/*"
