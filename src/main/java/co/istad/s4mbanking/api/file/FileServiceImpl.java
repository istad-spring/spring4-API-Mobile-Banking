package co.istad.s4mbanking.api.file;

import co.istad.s4mbanking.api.file.web.FileDto;
import co.istad.s4mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.base-url}")
    private String fileBaseUrl;

    private FileUtil fileUtil;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {

        String extension = fileUtil.getExtension(file.getOriginalFilename());
        String name = UUID.randomUUID().toString();
        Long size = file.getSize();
        String url = String.format("%s%s", fileBaseUrl, name);

        return FileDto.builder()
                .name(name)
                .extension(extension)
                .size(size)
                .url(url)
                .build();
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        return null;
    }
}
