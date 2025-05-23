// vars/docker_push.groovy
def call(String ProjectName, String ImageTag, String dockerHubUsernameParam, String credentialsId){ // Renamed a parameter for clarity
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) { // Use uppercase for env vars
        // Use --password-stdin for secure login
        sh "echo \"${DOCKER_PASS}\" | docker login -u \"${DOCKER_USER}\" --password-stdin"
        
        // Push the image using the variables from withCredentials
        sh "docker push ${DOCKER_USER}/${ProjectName}:${ImageTag}"
    }
}
