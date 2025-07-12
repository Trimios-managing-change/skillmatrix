import React from "react";
import StudentHeader from "../components/student/StudentHeader";
import StudentSidebar from "../components/student/StudentSidebar";
import StudentTrainingContent from "../components/student/StudentTrainingContent";

const StudentTrainingPage = () => {
  return (
    <div className="student-home-container">
      <StudentHeader />
      <div className="student-home-content">
        <StudentSidebar />
        <div className="student-main-area">
          <StudentTrainingContent />
        </div>
      </div>
    </div>
  );
};

export default StudentTrainingPage;
