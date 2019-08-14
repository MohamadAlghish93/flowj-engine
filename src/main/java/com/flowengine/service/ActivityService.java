package com.flowengine.service;

import com.flowengine.dao.ActivityDao;
import com.flowengine.dao.VariableFileDao;
import com.flowengine.entity.Activity;
import com.flowengine.entity.VariableFile;
import com.flowengine.entity.helper.HelperVariableFile;
import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.HelperObj;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class ActivityService {

    @Autowired
    ActivityDao activityDao;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    VariableFileDao variableFileDao;

    public Activity saveActivity(Activity activity) {
        return this.activityDao.save(activity);
    }

    public Optional<Activity> findActivityById(UUID id) {
        return this.activityDao.findById(id);
    }

    public List<Activity> findAllByProcessId(UUID processId) {
        return this.activityDao.findAllByProcessId(processId);
    }

    public VariableFile addFile(HelperVariableFile helperVariableFile, MultipartFile file) {
        VariableFile variableFile = null;
        if (file != null) {
            VariableFile tempVariableFile = new VariableFile();
            try {
                tempVariableFile.setId(HelperObj.GenerateUUID());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Add files
            String extenstion = "";
            String[] fileSpilted = file.getOriginalFilename().toString().split(Pattern.quote("."));
            if (fileSpilted.length >= 1) {
                extenstion = fileSpilted[1];
            }
            String uploadPath = ConstantsApp.UPLOAD_PATH + helperVariableFile.getGuidActivitty() + "/";
            String filePath = fileStorageService.storeFile(file, tempVariableFile.getId().toString() + "." + extenstion, uploadPath);

            tempVariableFile.setURL(uploadPath + filePath);
            tempVariableFile.setVariableId(helperVariableFile.getGuidVariable());
            variableFile =this.variableFileDao.save(tempVariableFile);

        }

        return variableFile;
    }
}
