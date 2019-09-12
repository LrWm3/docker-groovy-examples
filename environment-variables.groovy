def env = System.getenv()
//Print all the environment variables.

// This will print them all out, its a lot so I've commented it out
// env.each{
// println it
// }

// You can also access the specific variable, say 'username', as show below
String user= env['HOME']
println "Your HOME directory is pointed to: " + user