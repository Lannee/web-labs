fileName = Lab3
from = .\target
to = ..\Lab2\wildfly\wildfly-30.0.0.Beta1\standalone\deployments
run = ..\Lab2\wildfly\wildfly-30.0.0.Beta1\bin\standalone.bat

compile: pom.xml
	mvn clean install

deploy: $(from)\$(fileName).war
	copy $(from)\$(fileName).war $(to)\

run: compile deploy
	$(run)