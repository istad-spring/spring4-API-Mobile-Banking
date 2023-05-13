package co.istad.s4mbanking.api.file;

import co.istad.s4mbanking.api.file.web.FileDto;
import co.istad.s4mbanking.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {



    private FileUtil fileUtil;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> filesDto = new ArrayList<>();

        for (MultipartFile file : files) {
            FileDto fileDto = fileUtil.upload(file);
            filesDto.add(fileDto);
        }

        return filesDto;
    }

    @Override
    public void delete(String name) {

        try {
            FileDto fileDto = findByName(name);
            if (fileDto != null) {
                fileUtil.delete(name);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

    }

    @Override
    public FileDto findByName(String name) throws IOException {

        Resource resource = fileUtil.load(name);

        if (resource.exists()) {
            return FileDto.builder()
                    .name(resource.getFilename())
                    .extension(fileUtil.getExtension(resource.getFilename()))
                    .size(resource.contentLength())
                    .url(fileUtil.getUrl(resource.getFilename()))
                    .downloadUrl(fileUtil.getDownloadUrl(resource.getFilename()))
                    .build();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "File is not found");
    }

    @Override
    public Resource download(String name) {
        return fileUtil.load(name);
    }
}
