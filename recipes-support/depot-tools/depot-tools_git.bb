LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c2c05f9bdd5fc0b458037c2d1fb8d95e"

FILESEXTRAPATHS_prepend_poky := "${THISDIR}/files:"

SRCREV = "${AUTOREV}"
SRC_URI = "git://chromium.googlesource.com/chromium/tools/depot_tools;protocol=https \
           file://ca-certificates.crt;name=certs"

S = "${WORKDIR}/git"

do_patch() {
    sed -i -r 's:(#!\s?/usr/bin/env python\s*$):\13:' ./git/gclient.py
#    find ./ -type f -regextype posix-extended -regex ".*\.py" -execdir sed -i -r 's:(#!\s?/usr/bin/env python\s*$):\13:' '{}' \;
}

do_install() {
    install -d ${D}/${bindir}/depot_tools
    cp -r ${S}/* ${D}${bindir}/depot_tools

    install -m 644 ${WORKDIR}/ca-certificates.crt ${D}${bindir}/depot_tools
}

SYSROOT_DIRS =+ "${bindir}"

FILES_${PN}-dev = "${bindir}/depot_tools/*"

INSANE_SKIP_${PN} = "already-stripped"

BBCLASSEXTEND =+ "native nativesdk"

# vim:set ts=4 sw=4 sts=4 expandtab:
