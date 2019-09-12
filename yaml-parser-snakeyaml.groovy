////
// example of parsing yaml in groovy using snakeyaml
////
// Snakeyaml is a little more powerful so it should probably be used instead of eo-yaml, which i looked at before
// it also seems a lot simpler than eo-yaml
////
// https://mvnrepository.com/artifact/org.yaml/snakeyaml
@Grab(group='org.yaml', module='snakeyaml', version='1.25')
import org.yaml.snakeyaml.Yaml


InputStream input = new FileInputStream(new File("example.yaml"));
Yaml yaml = new Yaml();
Map<String,Object> data = yaml.load(input);
println "How to print .hello: " +data["hello"]

println "How to print .hello.value: " + data["hello"]["value"]

println "How to process all values in .hello.factsoflife"

data["hello"]["factsoflife"].each{
  println "Its a fact of life: " + it
}