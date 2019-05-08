package com.ncs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ncs.common.pojo.EasyDataGridResult;
import com.ncs.common.pojo.SmResult;
import com.ncs.pojo.TbItem;
import com.ncs.service.ItemService;

/**
 * 
 * @Title: ItemController.java
 * @Package com.ncs.controller
 * @Description: TODO(商品控制器)
 * @author: Stephen Yang
 * @date: Mar 26, 2019 11:42:01 PM
 * @version V1.0
 * @Copyright: 2019 Inc. All rights reserved.
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	/**
	 * find item by it itemId resultful api format GET
	 * "http://localhost:8080/item/{itemId}
	 * 
	 * @param itemId
	 * @return json item
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/item/{itemId}")
	public TbItem findItemById(@PathVariable Long itemId) throws Exception {

		TbItem item = itemService.selectItemById(itemId);
		return item;
	}

	/**
	 * find the item list use pageHeleper to pagination GET
	 * "http://localhost:8080/item/list?page=1&rows=30".
	 * 
	 * @return json items
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/item/list")
	public EasyDataGridResult findItemList(Integer page, Integer rows) throws Exception {

		// use pageHelper to Pagination
		EasyDataGridResult result = itemService.selectItemByPage(page, rows);

		return result;
	}

	/**
	 * 後台創建商品，保存商品信息及其描述,及其规格参数详细信息（描述是由富文本編輯器生成出來的html）
	 * 
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	public SmResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		SmResult result = itemService.createItem(item, desc, itemParams);
		return result;
	}

	/**
	 * 删除指定的商品
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/item/delete", method = RequestMethod.POST)
	public SmResult deleteItem(Long[] ids) throws Exception {
		SmResult result = itemService.deleteItem(ids);
		return result;
	}

	/**
	 * 商品下架
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/item/outstock", method = RequestMethod.POST)
	public SmResult outstockItem(Long[] ids) throws Exception {
		SmResult result = itemService.outstockItem(ids);
		return result;
	}

	/**
	 * 商品上架
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/item/reshelf", method = RequestMethod.POST)
	public SmResult reshelfItem(Long[] ids) throws Exception {
		SmResult result = itemService.reshelfItem(ids);
		return result;
	}

	/**
	 * 根据商品Id查询商品描述信息
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/item/query/item/desc/{itemId}", method = RequestMethod.GET)
	public SmResult queryItemDesc(@PathVariable("itemId") Long itemId) throws Exception {
		SmResult result = itemService.queryItemDesc(itemId);
		return result;
	}

	/**
	 * 根据商品Id查询商品规格参数
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/item/param/item/query/{itemId}", method = RequestMethod.GET)
	public SmResult queryItemParam(@PathVariable("itemId") Long itemId) throws Exception {
		SmResult result = itemService.queryItemParam(itemId);
		return result;
	}

	/**
	 * 更新商品详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/rest/item/update", method = RequestMethod.POST)
	public SmResult updateItemDetails(TbItem item, String desc, String itemParams) throws Exception {
		
		SmResult result = itemService.updateItem(item, desc, itemParams);
		return result;

	}

}