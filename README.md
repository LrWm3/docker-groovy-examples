# docker-groovy-examples

This projects gives a rough overview of how to use docker and groovy together as a script-runner platform for a cloud-based environment.

Hopefully the main building blocks are all here

Currently the `mongo-test.yaml` and `kafka-test.yaml` contain nothing.

## Overview

An overview of the various files in this project.

### docker-build-example.sh

this file can be run to build the initial docker registry. Access to the central maven repo is required for this though, so it might not work on all systems

### docker-run-default.sh

this file can be run once the image is built. Crack it open to see how it works.

### docker-run-envvar-script.sh && docker-run-hello-world-script.sh

these files show how to run scripts which are not defined as the docker 'command'

### \*.groovy files

these files are example groovy files. The `Dockerfile` copies any files in the root directory of this project ending in `.groovy` into the docker image on build, on path `/home/groovy/<filename>`. Since the working directory for this image is `/home/groovy`, this means scripts can be run without specifying the full-path.

### Dockerfile

this builds the docker image. it is split up into two stages as I was having trouble getting it working in a single stage

### example.yaml

this file is used to test the `yaml-parser-snakeyaml.groovy` script.

## Usage

Hopefully this works

```bash
# needs a docker daemon running and central maven access...
./docker-build-example.sh

# if previous stage succeeds, rest will succeed and demo things
./docker-run-default-cmd.sh

# crack one of these open to see how to modify initial command with `docker run`
./docker-run-envvar-script.sh
./docker-run-hello-world-script.sh
```
