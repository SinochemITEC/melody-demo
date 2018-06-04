package com.eyeieye.melody.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.eyeieye.melody.demo.domain.Resource;
import com.eyeieye.melody.demo.enums.ResourceType;
import com.eyeieye.melody.demo.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	private static final Map<String, List<Resource>> RESOURCES;
	static {
		RESOURCES = new HashMap<String, List<Resource>>();
		List<Resource> resources = new ArrayList<Resource>();
		resources.add(new Resource("’„Ω≠", "zj", "province", 1));
		RESOURCES.put("province", resources);
	}

	public List<Resource> getResourcesByType(ResourceType type) {
		if (type == null) {
			return null;
		}
		return RESOURCES.get(type.getName());
	}

}
