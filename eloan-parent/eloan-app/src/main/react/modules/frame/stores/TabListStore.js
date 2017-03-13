var Reflux = require('reflux');
import React from 'react';
var AppActions = require('../actions/AppActions');
var Workbench = require('../../Public/Workbench/Index')
export default Reflux.createStore({
	listenables: [AppActions],
	tablist: [{
		'key': 'workbench',
		'tabName': '工作台',
		"tabId": 'workbench'
	}],
	activeId: 'workbench',
	onSetTabActiveKey(tabName, tabId) {
		var me = this;
		var tablist = window.tablist || this.tablist;
		var flag = false;
		var activeId = tabId;
		tablist.forEach((v) => {
			if (tabId === v.tabId) {
				flag = true; //当前activeKey 在tablist中已经存在
				return
			}
		});
		var tabContent;

		if (!flag) { // 点击左侧菜单时，没有相应标签页
			var Component = Workbench;

			switch (tabId) {
				case 'sysUserManage':
					{ //用户管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/UserMang/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'UserMang');
						break;
					}
				case 'sysOfficeManage':
					{ //组织管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/OrganizationMang/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'OrganizationMang');
						break;
					}
				case 'sysRoleManage':
					{ //角色管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/RoleMang/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'RoleMang');
						break;
					}
				case 'sysMenuManage':
					{ //菜单管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/MenuMang/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'MenuMang');
						break;
					}
				case 'sysFelManage':
					{ //计算公式配置

						require.ensure([], function (require) {
							Component = require('../../SystemMng/FelManag/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'FelManag');
						break;
					}
				case 'sysDicManage':
					{ //字典管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/DictionaryMang/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'DictionaryMang');
						break;
					}
				case 'sysBankManage':
					{ //银行账号管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/BankMang/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'BankMang');
						break;
					}
				case 'sysProductParametersManage':
					{ //产品参数管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/ProductParametersManag/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'ProductParametersManag');
						break;
					}
				case 'sysProductTypeManage':
					{ //产品类型管理

						require.ensure([], function (require) {
							Component = require('../../SystemMng/ProductTypeManag/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'ProductTypeManag');
						break;
					}
				case 'CustomerEvaluation':
					{ //客服初评

						require.ensure([], function (require) {
							Component = require('../../CustomerServiceTrial/CustomerEvaluation/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CustomerEvaluation');
						break;
					}
				case 'CustomerDocking':
					{ //客服对接

						require.ensure([], function (require) {
							Component = require('../../CustomerServiceTrial/CustomerDocking/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CustomerDocking');
						break;
					}
				case 'CustomerSurvey':
					{ //客服调查

						require.ensure([], function (require) {
							Component = require('../../CustomerServiceTrial/CustomerSurvey/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CustomerSurvey');
						break;
					}
				case 'CustomerConfirmation':
					{ //客服确认

						require.ensure([], function (require) {
							Component = require('../../CustomerServiceTrial/CustomerConfirmation/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CustomerConfirmation');
						break;
					}
				case 'CustomerConfirmation_xh':
					{ //客服确认下户

						require.ensure([], function (require) {
							Component = require('../../CustomerServiceTrial/CustomerConfirmation_xh/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CustomerConfirmation_xh');
						break;
					}
				case 'RefundApplication':
					{ //退费申请

						require.ensure([], function (require) {
							Component = require('../../CustomerServiceTrial/RefundApplication/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'RefundApplication');
						break;
					}
				case 'TaskAssignment':
					{ //下户任务分配

						require.ensure([], function (require) {
							Component = require('../../AuthorityCardMang/TaskAssignment/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'TaskAssignment');
						break;
					}
				case 'HouseholdRegistration':
					{ //权证下户

						require.ensure([], function (require) {
							Component = require('../../AuthorityCardMang/HouseholdRegistration/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'HouseholdRegistration');
						break;
					}
				case 'SystemParameterSettings':
					{ //系统参数设置

						require.ensure([], function (require) {
							Component = require('../../SystemMng/SystemParameterSettings/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'SystemParameterSettings');
						break;
					}
				case 'ContractSigning':
					{ //合同签订

						require.ensure([], function (require) {
							Component = require('../../ContractManagement/ContractSigning/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'ContractSigning');
						break;
					}
				case 'ContractList':
					{ //合同列表

						require.ensure([], function (require) {
							Component = require('../../ContractManagement/ContractList/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'ContractList');
						break;
					}
				case 'SolutionTaskAssignment':
					{ //解押任务分配

						require.ensure([], function (require) {
							Component = require('../../AuthorityCardMang/SolutionTaskAssignment/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'SolutionTaskAssignment');
						break;
					}
				case 'MortgageTaskAllocation':
					{ //抵押任务分配

						require.ensure([], function (require) {
							Component = require('../../AuthorityCardMang/MortgageTaskAllocation/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'MortgageTaskAllocation');
						break;
					}
				case 'ForInterviewing':
					{ //面审

						require.ensure([], function (require) {
							Component = require('../../AuditManagement/ForInterviewing/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'ForInterviewing');
						break;
					}
				
				case 'DocumentsFill':
					{ //放款单据填写2

						require.ensure([], function (require) {
							Component = require('../../AuditManagement/DocumentsFill/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'DocumentsFill');
						break;
					}
				case 'FinalJudgment':
					{ //终审

						require.ensure([], function (require) {
							Component = require('../../AuditManagement/FinalJudgment/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'FinalJudgment');
						break;
					}
				case 'Reexamination':
					{ //复审

						require.ensure([], function (require) {
							Component = require('../../AuditManagement/Reexamination/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'Reexamination');
						break;
					}
				case 'ConfirmLending':
					{ //放款确认

						require.ensure([], function (require) {
							Component = require('../../AuditManagement/ConfirmLending/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
					 	}, 'ConfirmLending');
						break;
					}
				case 'LoanManagement':
					{ //财务管理-放款管理

						require.ensure([], function (require) {
							Component = require('../../FinancialManagement/LoanManagement/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'LoanManagement');
						break;
					}
				case 'CostManagement_xh':
					{ //财务管理-下户费收费

						require.ensure([], function (require) {
							Component = require('../../FinancialManagement/CostManagement_xh/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CostManagement_xh');
						break;
					}
				case 'AccountManagement':
					{ //财务管理-下户费管理

						require.ensure([], function (require) {
							Component = require('../../FinancialManagement/AccountManagement/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'AccountManagement');
						break;
					}
				case 'CommissionManagement':
					{ //财务管理-返佣管理

						require.ensure([], function (require) {
							Component = require('../../FinancialManagement/CommissionManagement/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CommissionManagement');
						break;
					}
				case 'RefundManagement_xh':
					{ //财务管理-下户费退费

						require.ensure([], function (require) {
							Component = require('../../FinancialManagement/RefundManagement_xh/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'RefundManagement_xh');
						break;
					}
				
				case 'AddInformation':
					{ //补充资料

						require.ensure([], function (require) {
							Component = require('../../AdditionalInformation/AddInformation/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'AddInformation');
						break;
					}
				case 'MortgageRegistration':
					{ //押品管理-抵押登记

						require.ensure([], function (require) {
							Component = require('../../CollateralManagement/MortgageRegistration/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'MortgageRegistration');
						break;
					}
				case 'CollateralSolution':
					{ //押品管理-押品解押

						require.ensure([], function (require) {
							Component = require('../../CollateralManagement/CollateralSolution/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'CollateralSolution');
						break;
					}
				case 'RepaymentManagement':
					{ //贷后管理-还款管理

						require.ensure([], function (require) {
							Component = require('../../PostLoanManagement/RepaymentManagement/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'RepaymentManagement');
						break;
					}
				case 'SettlementManagement':
					{ //贷后管理-结算管理

						require.ensure([], function (require) {
							Component = require('../../PostLoanManagement/SettlementManagement/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'SettlementManagement');
						break;
					}
				case 'JustRegister':
					{ //公正管理-公正登记

						require.ensure([], function (require) {
							Component = require('../../NotaryManagement/JustRegister/Index');
							tabContent = <Component />;
							me.updataTablist(tabId, tabName, tabContent, tablist);
						}, 'JustRegister');
						break;
					}

			}
		} else this.update(activeId, tablist);

	},
	updataTablist(tabId, tabName, tabContent, tablist) {
		tablist = tablist.concat({
			key: tabId,
			tabName: tabName,
			tabId: tabId,
			tabContent: tabContent
		})
		this.update(tabId, tablist);
	},
	onRemoveTab(tabId) {
		var tablist = window.tablist || this.tablist;
		var foundIndex = 0;
		tablist = tablist.filter(function (t, index) {
			if (t.tabId !== tabId) {
				return true;
			} else {
				foundIndex = index;
				return false;
			}
		});
		var activeId = window.activeId || this.activeId;
		if (activeId === tabId) {
			if (foundIndex) {
				foundIndex--;
			}
			activeId = tablist[foundIndex].tabName;
		}
		this.update(activeId, tablist);
	},
	update(activeId, tablist) {
		window.tablist = tablist;
		window.activeId = activeId
		this.trigger({
			activeId: activeId,
			tablist: tablist
		});
	}
}); 
