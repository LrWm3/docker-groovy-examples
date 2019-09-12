# Dockerfile build stage for groovy + java deps
## I tried jr11, but was getting a lot of illegal reflection errors
## switched to jre8 after reading this: https://stackoverflow.com/a/46457572
FROM groovy:jre11 as builder

# I don't know why but it seems I need to copy deps to a non-standard location during the build-phase
RUN grape install io.fabric8 kubernetes-client 4.5.1 && \
  grape install org.yaml snakeyaml 1.25 && \
  grape install org.mongodb mongodb-driver 3.4.3 && \
  grape install org.apache.kafka kafka-clients 2.3.0 && \
  grape install org.codehaus.groovy.modules.http-builder http-builder 0.7 && \
  cp -r ~/.groovy/grapes /home/groovy/groovy-deps && \
  grape list

# Create image phase
FROM groovy:jre11

##
# turn off java warnings in the image itself to avoid 'reflection warnings' on running anything with groovy by
# default; can be overridden if its really needed
##
# option B: switch to groovy:jre8...
ENV GROOVY_TURN_OFF_JAVA_WARNINGS=true

# Add java deps from non-standard location in original image to expected location, with groovy (1000) as owner
COPY --from=builder --chown=1000 /home/groovy/groovy-deps /home/groovy/.groovy/grapes 

# Add resources, with groovy as owner
COPY --chown=1000 ./example.yaml /home/groovy/example.yaml

# Add test script as entrypoint, with groovy as owner
COPY --chown=1000 ./yaml-parser-snakeyaml.groovy /home/groovy/entrypoint.groovy

# Add some extra test scripts to demonstrate how to switch between groovy scripts
COPY --chown=1000 ./*.groovy /home/groovy/

# Run whatever is copied as 'entrypoint.groovy'
CMD [ "groovy", "entrypoint.groovy" ]