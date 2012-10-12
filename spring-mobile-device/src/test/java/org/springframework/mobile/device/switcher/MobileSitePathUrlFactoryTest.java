package org.springframework.mobile.device.switcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public final class MobileSitePathUrlFactoryTest extends AbstractSitePathUrlFactoryTest {
	
	@Before
	public void setUp() {
		super.setUp();
		this.rootFactory = new MobileSitePathUrlFactory("/mobile");
		this.pathFactory = new MobileSitePathUrlFactory("/mobile", "/showcase");
	}
	
	@Test
	public void rootFullNormalPath() {
		assertEquals("/", rootFactory.getFullNormalPath());
	}
	
	@Test
	public void rootFullMobilePath() {
		assertEquals("/mobile/", rootFactory.getFullMobilePath());
	}
	
	@Test
	public void rootFullTabletPath() {
		assertNull(rootFactory.getFullTabletPath());
	}
	
	@Test
	public void rootCleanNormalPath() {
		assertEquals("", rootFactory.getCleanNormalPath());
	}
	
	@Test
	public void rootCleanMobilePath() {
		assertEquals("/mobile", rootFactory.getCleanMobilePath());
	}
	
	@Test
	public void rootCleanTabletPath() {
		assertNull(rootFactory.getCleanTabletPath());
	}	
	
	@Test
	public void pathFullNormalPath() {
		assertEquals("/showcase/", pathFactory.getFullNormalPath());
	}
	
	@Test
	public void pathFullMobilePath() {
		assertEquals("/showcase/mobile/", pathFactory.getFullMobilePath());
	}
	
	@Test
	public void pathFullTabletPath() {
		assertNull(pathFactory.getFullTabletPath());
	}
	
	@Test
	public void pathCleanNormalPath() {
		assertEquals("/showcase", pathFactory.getCleanNormalPath());
	}
	
	@Test
	public void pathCleanMobilePath() {
		assertEquals("/showcase/mobile", pathFactory.getCleanMobilePath());
	}
	
	@Test
	public void pathCleanTabletPath() {
		assertNull(pathFactory.getCleanTabletPath());
	}
	
	@Test
	public void isRequestForRootSite() {
		request.setRequestURI("/mobile/");
		assertTrue(rootFactory.isRequestForSite(request));
	}
	
	@Test
	public void isRequestForPathSite() {
		request.setRequestURI("/showcase/mobile/");
		assertTrue(pathFactory.isRequestForSite(request));
	}
	
	@Test
	public void notRequestForRootSite() {
		request.setRequestURI("/");
		assertFalse(rootFactory.isRequestForSite(request));
	}
	
	@Test
	public void notRequestForPathSite() {
		request.setRequestURI("/showcase/");
		assertFalse(pathFactory.isRequestForSite(request));
	}
	
	@Test
	public void notRequestForRootSiteWithPath() {
		request.setRequestURI("/marvelous/");
		assertFalse(rootFactory.isRequestForSite(request));
	}
	
	@Test
	public void notRequestForPathSiteWithPath() {
		request.setRequestURI("/showcase/marvelous/");
		assertFalse(pathFactory.isRequestForSite(request));
	}

	@Test
	public void createRootSiteUrl() {
		request.setServerPort(80);
		request.setRequestURI("/bar");
		assertEquals("http://www.app.com/mobile/bar", rootFactory.createSiteUrl(request));
	}
	
	@Test
	public void createRootSiteUrlPort8080() {
		request.setServerPort(8080);
		request.setRequestURI("/bar");
		assertEquals("http://www.app.com:8080/mobile/bar", rootFactory.createSiteUrl(request));
	}
	
	@Test
	public void createPathSiteUrl() {
		request.setServerPort(80);
		request.setRequestURI("/showcase/bar");
		assertEquals("http://www.app.com/showcase/mobile/bar", pathFactory.createSiteUrl(request));
	}
	
	@Test
	public void createPathSiteUrlPort8080() {
		request.setServerPort(8080);
		request.setRequestURI("/showcase/bar");
		assertEquals("http://www.app.com:8080/showcase/mobile/bar", pathFactory.createSiteUrl(request));
	}
}
