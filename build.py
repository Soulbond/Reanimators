class Compile:
	def __init__(self):
		self.run()

	def run(self):
		import os
		os.system("cd Reanimators && gradlew setupDecompWorkspace --stop && gradlew clean build")
		
		import shutil
		try:
			shutil.copyfile("Reanimators/build/libs/Reanimators-b1.jar", os.getenv("APPDATA") + "\\.minecraft\\mods\\Reanimators-b3.jar")
			os.system("start C:/Program Files (x86)/Minecraft Launcer/MinecraftLauncher")
			print("You got it champ!")
		except:
			print("Error!")

		import sys
		sys.exit()

Compile()