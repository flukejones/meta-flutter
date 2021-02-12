LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fcc629d7a228e5e3e54c2fa1d73f774"

SRCREV = "8f574b92ad3dc100458d89e6f2683f1bcd1da71e"
SRC_URI = "git://github.com/HidetoKimura/flutter_wayland;protocol=https;branch=waylandpp"

DEPENDS =+ " flutter-engine wayland mesa waylandpp libxkbcommon rapidjson"

RDEPENDS_${PN} = " flutter-engine wayland mesa freetype waylandpp libxkbcommon rapidjson"

REQUIRED_DISTRO_FEATURES = "wayland"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
    -DCMAKE_VERBOSE_MAKEFILE=TRUE \
    -DFLUTTER_ENGINE_LIBRARY=${STAGING_LIBDIR}/libflutter_engine.so \
    "

FILES_${PN}  = "${bindir}/flutter_wayland"
