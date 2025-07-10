package com.lms.studentmanagement.controller;

import com.lms.studentmanagement.dto.LessonResultDTO;
import com.lms.studentmanagement.model.LessonResult;
import com.lms.studentmanagement.service.impl.LessonResultServiceImpl;
import com.lms.studentmanagement.service.LessonResultService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson-results")
public class LessonResultController {
    private final LessonResultService lessonResultService;
    private final LessonResultServiceImpl lessonResultServiceImpl;

    public LessonResultController(LessonResultService lessonResultService, LessonResultServiceImpl lessonResultServiceImpl) {
        this.lessonResultService = lessonResultService;
        this.lessonResultServiceImpl = lessonResultServiceImpl;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping("/submit/{lessonId}")
    public LessonResult submitLessonResult(@PathVariable Long lessonId, @RequestBody LessonResult result, @RequestParam Long studentId) {
        return lessonResultService.submitLessonResult(lessonId, result, studentId);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PatchMapping("/review/{resultId}")
    public LessonResult reviewLessonResult(@PathVariable Long resultId, @RequestParam("status") LessonResult.Status status,
                                           @RequestParam("comment") String comment, @RequestParam("teacherId") Long teacherId) {
        return lessonResultService.reviewLessonResult(resultId, status, comment, teacherId);
    }

    @GetMapping("/with-exam-score")
    public LessonResultDTO getLessonResultWithExamScore(@RequestParam Long lessonId, @RequestParam Long userId) {
        return lessonResultServiceImpl.getLessonResultWithExamScore(lessonId, userId);
    }
}

