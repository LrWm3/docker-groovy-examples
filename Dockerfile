FROM groovy:jre11 as builder

RUN grape install io.fabric8 kubernetes-client 4.5.1 && \
  grape install org.yaml snakeyaml 1.2.5 && \
  cp ~/.groovy/grapes /home/groovy/groovy-deps && \
  grape list

FROM groovy:jre11

COPY --from=builder --chown=1000 /home/groovy/groovy-deps /home/groovy/.groovy/grapes 
COPY ./yaml-parser.groovy /home/groovy/entrypoint.groovy
COPY ./example.yaml /home/groovy/example.yaml

CMD [ "groovy", "entrypoint.groovy" ]