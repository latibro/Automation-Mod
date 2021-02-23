package latibro.automation.core

enum LinkType {

    STATIC("static"),
    DYNAMIC("dynamic"),
    SNAPSHOT("snapshot")

    private final String name

    private LinkType(String name) {
        this.name = name
    }

    @Override
    String toString() {
        return name
    }

}