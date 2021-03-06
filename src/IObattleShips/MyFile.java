package IObattleShips;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.*;

class MyFile {

	public static String readFileAsString(String fileName) throws IOException {
		return new String(Files.readAllBytes(Paths.get(fileName)));
	}

	public static int writeIntoFile(String fileName, String text, boolean append) {
		try (FileWriter fileWriter = new FileWriter(fileName, append)) {
			fileWriter.write(text);
		} catch (IOException e) {
			System.out.printf("An exception occurs %s", e.getMessage());
		}
		return 0;
	}

	public static boolean fileNameExist(String filename, String pathdir) {
		Path dir = Paths.get(pathdir);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path file: stream) {
				String tempFileName = file.getFileName().toString();
				if (filename.equals(tempFileName))
					return true;
			}
		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}
		return false;
	}

	public static void pushList(String pathdir, Interface iface) {
		Path dir = Paths.get(pathdir);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path file: stream) {
				String tempFileName = file.getFileName().toString();
				iface.putstr("\t" + tempFileName + "\n");
			}
		} catch (IOException | DirectoryIteratorException x) {
			// IOException can never be thrown by the iteration.
			// In this snippet, it can only be thrown by newDirectoryStream.
			System.err.println(x);
		}
	}

	public static String getFileName(String path) {
		File f = new File(path);

		return f.getName();
	}
}
