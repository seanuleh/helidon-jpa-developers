Helidon Notes:

Use Quick Start:
```
mvn -U archetype:generate \
    -DinteractiveMode=false \
    -DarchetypeGroupId=io.helidon.archetypes \
    -DarchetypeArtifactId=helidon-bare-mp \
    -DarchetypeVersion=2.2.2 \
    -DgroupId=io.helidon.example \
    -DartifactId=helidon-jpa \
    -Dpackage=io.helidon.example.jpa
```

Add dependencies to pom:
```
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.helidon.integrations.cdi</groupId>
            <artifactId>helidon-integrations-cdi-datasource-hikaricp</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.helidon.integrations.cdi</groupId>
            <artifactId>helidon-integrations-cdi-jta-weld</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.helidon.integrations.cdi</groupId>
            <artifactId>helidon-integrations-cdi-jpa</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.helidon.integrations.cdi</groupId>
            <artifactId>helidon-integrations-cdi-eclipselink</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>jakarta.transaction</groupId>
            <artifactId>jakarta.transaction-api</artifactId>
            <scope>provided</scope>
        </dependency>
```
