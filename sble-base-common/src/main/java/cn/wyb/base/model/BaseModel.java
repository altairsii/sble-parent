package cn.wyb.base.model;

public class BaseModel
{

	/**
	 * 创建时间
	 */
	private String createDate;

	/**
	 * 修改时间
	 */
	private String modifyDate;

	/**
	 * 删除时间
	 */
	private String deleteDate;

	/**
	 * 备注
	 */
	private String remark;

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getModifyDate()
	{
		return modifyDate;
	}

	public void setModifyDate(String modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	public String getDeleteDate()
	{
		return deleteDate;
	}

	public void setDeleteDate(String deleteDate)
	{
		this.deleteDate = deleteDate;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

}
