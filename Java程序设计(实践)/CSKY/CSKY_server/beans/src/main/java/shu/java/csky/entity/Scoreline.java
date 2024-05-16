package shu.java.csky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName scoreline
 */
@TableName(value = "scoreline")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scoreline implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "school_id")
    private Integer schoolId;

    /**
     *
     */
    @TableField(value = "college_name")
    private String collegeName;

    /**
     *
     */
    @TableField(value = "major_code")
    private String majorCode;

    /**
     *
     */
    @TableField(value = "major_name")
    private String majorName;

    /**
     *
     */
    @TableField(value = "major_dir")
    private String majorDir;

    /**
     *
     */
    @TableField(value = "score_21")
    private Integer score21;

    /**
     *
     */
    @TableField(value = "score_20")
    private Integer score20;

    /**
     *
     */
    @TableField(value = "score_19")
    private Integer score19;

    /**
     *
     */
    @TableField(value = "english_subject")
    private String englishSubject;

    /**
     *
     */
    @TableField(value = "math_subject")
    private String mathSubject;

    /**
     *
     */
    @TableField(value = "politics_subject")
    private String politicsSubject;

    /**
     *
     */
    @TableField(value = "computer_subject")
    private String computerSubject;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Scoreline other = (Scoreline) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getSchoolId() == null ? other.getSchoolId() == null : this.getSchoolId().equals(other.getSchoolId()))
                && (this.getCollegeName() == null ? other.getCollegeName() == null : this.getCollegeName().equals(other.getCollegeName()))
                && (this.getMajorCode() == null ? other.getMajorCode() == null : this.getMajorCode().equals(other.getMajorCode()))
                && (this.getMajorName() == null ? other.getMajorName() == null : this.getMajorName().equals(other.getMajorName()))
                && (this.getMajorDir() == null ? other.getMajorDir() == null : this.getMajorDir().equals(other.getMajorDir()))
                && (this.getScore21() == null ? other.getScore21() == null : this.getScore21().equals(other.getScore21()))
                && (this.getScore20() == null ? other.getScore20() == null : this.getScore20().equals(other.getScore20()))
                && (this.getScore19() == null ? other.getScore19() == null : this.getScore19().equals(other.getScore19()))
                && (this.getEnglishSubject() == null ? other.getEnglishSubject() == null : this.getEnglishSubject().equals(other.getEnglishSubject()))
                && (this.getMathSubject() == null ? other.getMathSubject() == null : this.getMathSubject().equals(other.getMathSubject()))
                && (this.getPoliticsSubject() == null ? other.getPoliticsSubject() == null : this.getPoliticsSubject().equals(other.getPoliticsSubject()))
                && (this.getComputerSubject() == null ? other.getComputerSubject() == null : this.getComputerSubject().equals(other.getComputerSubject()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSchoolId() == null) ? 0 : getSchoolId().hashCode());
        result = prime * result + ((getCollegeName() == null) ? 0 : getCollegeName().hashCode());
        result = prime * result + ((getMajorCode() == null) ? 0 : getMajorCode().hashCode());
        result = prime * result + ((getMajorName() == null) ? 0 : getMajorName().hashCode());
        result = prime * result + ((getMajorDir() == null) ? 0 : getMajorDir().hashCode());
        result = prime * result + ((getScore21() == null) ? 0 : getScore21().hashCode());
        result = prime * result + ((getScore20() == null) ? 0 : getScore20().hashCode());
        result = prime * result + ((getScore19() == null) ? 0 : getScore19().hashCode());
        result = prime * result + ((getEnglishSubject() == null) ? 0 : getEnglishSubject().hashCode());
        result = prime * result + ((getMathSubject() == null) ? 0 : getMathSubject().hashCode());
        result = prime * result + ((getPoliticsSubject() == null) ? 0 : getPoliticsSubject().hashCode());
        result = prime * result + ((getComputerSubject() == null) ? 0 : getComputerSubject().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", collegeName=").append(collegeName);
        sb.append(", majorCode=").append(majorCode);
        sb.append(", majorName=").append(majorName);
        sb.append(", majorDir=").append(majorDir);
        sb.append(", score21=").append(score21);
        sb.append(", score20=").append(score20);
        sb.append(", score19=").append(score19);
        sb.append(", englishSubject=").append(englishSubject);
        sb.append(", mathSubject=").append(mathSubject);
        sb.append(", politicsSubject=").append(politicsSubject);
        sb.append(", computerSubject=").append(computerSubject);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}