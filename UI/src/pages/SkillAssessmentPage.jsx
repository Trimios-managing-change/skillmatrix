import React from "react";
import StudentHeader from "../components/student/StudentHeader";
import StudentSidebar from "../components/student/StudentSidebar";
import SkillAssessmentContent from "../components/student/SkillAssessmentContent";
import "../pagescss/StudentPageLayout.css";

const SkillAssessmentPage = () => {
  return (
    <div className="student-home-container">
    <StudentHeader />
    <div className="student-home-content">
      <StudentSidebar />
      <div className="student-main-area">
        <SkillAssessmentContent />
      </div>
    </div>
  </div>
  
  );
};

export default SkillAssessmentPage;
