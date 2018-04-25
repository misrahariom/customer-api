pipeline{
	agent any
	stages
	{
		def app
		stage("Compile state")
		{
			steps
			{
				echo"compiling"
			}
		}
		stage("build state")
		{
			steps
			{
				echo"building"
				// there should be job name customer-api-build in jenkins to run below command
				build 'customer-api-build'
			}
		}
		stage("deploy state")
		{
			steps
			{
				echo"deploying"
			}
		}
	}
}