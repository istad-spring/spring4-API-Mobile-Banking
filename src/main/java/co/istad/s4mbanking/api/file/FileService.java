package co.istad.s4mbanking.api.file;

import co.istad.s4mbanking.api.file.web.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileDto uploadSingle(MultipartFile file);

    List<FileDto> uploadMultiple(List<MultipartFile> files);
}
