////
// Test REST api client 
////
// ref: https://stackoverflow.com/a/39186989
// async-ref: https://github.com/jgritman/httpbuilder/wiki/AsyncHTTPBuilder
////

// Apache 2.0 license: https://mvnrepository.com/artifact/org.codehaus.groovy.modules.http-builder/http-builder
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7')

// import static groovyx.net.http.ContentType.*
// import groovyx.net.http.AsyncHTTPBuilder
import groovyx.net.http.AsyncHTTPBuilder
import static groovyx.net.http.ContentType.HTML
 
def http = new AsyncHTTPBuilder(
                poolSize : 4,
                uri : 'http://hc.apache.org',
                contentType : HTML )
 
 
def result = http.get(path:'/') { resp, html ->
    println ' got async response!'
    return html
}
 
assert result instanceof java.util.concurrent.Future
 
while ( ! result.done ) {
    println 'waiting...'
    Thread.sleep(2000)
}
 
/* The Future instance contains whatever is returned from the response
   closure above; in this case the parsed HTML data: */
def html = result.get()
assert html instanceof groovy.util.slurpersupport.GPathResult
