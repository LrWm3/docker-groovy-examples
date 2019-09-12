@Grab('org.yaml:snakeyaml:1.2.5')
import org.yaml.snakeyaml.*

println "attempting to parse yaml file 'example.yaml'..." 

YamlMapping yamlObj = Yaml.createYamlInput(new File("example.yaml"))
    .readYamlMapping();

println "Received yaml:"
println yamlObj.toString()

println "Obtain a nested yaml value: " + yamlObj.yamlMapping("hello").string("value")
println "Obtain a single nested yaml list item: " + yamlObj.yamlMapping("hello").yamlSequence("factsoflife").string(0)

yamlObj.yamlMapping("hello").yamlSequence("factsoflife").string(0)

println "Obtain all items in a yaml list..."

yamlObj.yamlMapping("hello").yamlSequence("factsoflife").children().each{
  println "A yaml list item has value " + it
}

println "Done yaml parsing examples!"