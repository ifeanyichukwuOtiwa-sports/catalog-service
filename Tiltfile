# Build
custom_build(
    # Name of the container Image
    ref = 'catalog-service',
    # Command to build container Image
    command = './gradlew bootBuildImage --imageName $EXPECTED_REF',
    # Files to watch that triggers a new build
    deps = ['build.gradle', 'src'],
    disable_push=True
)

local_resource(
    name='Load catalog-service into minikube',
    cmd='minikube image load catalog-service --profile polar'
 )

# Deploy
k8s_yaml(['k8s/catalog-service/deployment.yml', 'k8s/catalog-service/service.yaml'])

# Manage
k8s_resource('catalog-service', port_forwards=['9001'])
